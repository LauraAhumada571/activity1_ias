package com.example.activity1.Infrastructure.Gateways;

import com.example.activity1.Core.Domain.MaintenanceId;
import com.example.activity1.Core.Domain.MaintenanceService;
import com.example.activity1.Core.Gateways.MaintenanceRepository;
import com.example.activity1.Infrastructure.Controller.models.MaintenanceDTO;
import com.example.activity1.Infrastructure.Controller.models.MaintenanceInput;
import com.example.activity1.Infrastructure.Gateways.Repositories.models.MaintenanceDBO;
import com.example.activity1.shared.domain.PageQuery;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Repository
public class SqlMaintenanceRepository implements MaintenanceRepository {
    private final DataSource dataSource;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public SqlMaintenanceRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<MaintenanceService> queryMaintenance(PageQuery pageQuery) {
        String sql = "SELECT * FROM maintenance LIMIT ? OFFSET ?";
        try(Connection connection =  dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, pageQuery.getLimit().getValue());
                preparedStatement.setInt(2, pageQuery.getSkip().getValue());

                ResultSet resultSet = preparedStatement.executeQuery();
                List<MaintenanceService> result = new ArrayList<>();

                while(resultSet.next()){
                    MaintenanceDBO maintenanceDBO = MaintenanceDBO.fromResulSet(resultSet);
                    MaintenanceService maintenanceService = maintenanceDBO.toDomain();
                    result.add(maintenanceService);
                }
            resultSet.close();

            return result;

        } catch (SQLException exception){
            throw new RuntimeException("Error querying database", exception);
        }
    }

    @Override
    public Optional<MaintenanceService> get(MaintenanceId maintenanceId) {
        String sql = "SELECT * FROM maintenance WHERE maintenance_id = ?";
        try (Connection connection =  dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, maintenanceId.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                MaintenanceDBO dbo = MaintenanceDBO.fromResulSet(resultSet);
                MaintenanceService domainMaintenance = dbo.toDomain();
                return Optional.of(domainMaintenance);
            } else {
                return Optional.empty();
            }

        }catch (SQLException exception){
            throw new RuntimeException("Error querying database", exception);
        }
    }

    @Override
    public void store(MaintenanceService maintenanceService) {
        String sql = "INSERT INTO maintenance (maintenance_id, start_date_time, end_date_time, description) VALUES (?, ?, ?, ?)";
        try (Connection connection =  dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            String parsedStartDateTime = maintenanceService.getStartDateTime().getValue().format(dateTimeFormatter);
            String parsedEndDateTime = maintenanceService.getEndDateTime().getValue().format(dateTimeFormatter);

            preparedStatement.setString(1, maintenanceService.getIdMaintenance().toString());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(parsedStartDateTime));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(parsedEndDateTime));
            preparedStatement.setString(4, maintenanceService.getDescription().toString());

            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            throw new RuntimeException("Error querying database", exception);
        }
    }

    @Override
    public MaintenanceId deleteMaintenance(MaintenanceId maintenanceId) {
        String sql = "DELETE FROM maintenance WHERE maintenance_id = ?";
        try (Connection connection =  dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, maintenanceId.toString());
            preparedStatement.executeUpdate();

        }catch (SQLException exception){
            throw new RuntimeException("Error querying database", exception);
        }
        return maintenanceId;
    }

    @Override
    public MaintenanceInput updateMaintenance(MaintenanceId maintenanceId, MaintenanceInput maintenanceInput) {
        String sql = "UPDATE maintenance SET start_date_time = ?, end_date_time = ?, description = ? WHERE maintenance_id = ?";
        try(Connection connection =  dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            String parsedStartDateTime = maintenanceInput.getStartDateTime().format(dateTimeFormatter);
            String parsedEndDateTime = maintenanceInput.getEndDateTime().format(dateTimeFormatter);

            preparedStatement.setTimestamp(1, Timestamp.valueOf(parsedStartDateTime));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(parsedEndDateTime));
            preparedStatement.setString(3, maintenanceInput.getDescription());
            preparedStatement.setString(4, maintenanceId.toString());

            preparedStatement.executeUpdate();
        }catch (SQLException exception){
            throw new RuntimeException("Error querying database", exception);
        }
        return null;
    }


}
