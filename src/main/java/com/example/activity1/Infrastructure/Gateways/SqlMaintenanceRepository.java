package com.example.activity1.Infrastructure.Gateways;

import com.example.activity1.Core.Domain.MaintenanceId;
import com.example.activity1.Core.Domain.MaintenanceService;
import com.example.activity1.Core.Gateways.MaintenanceRepository;
import com.example.activity1.Infrastructure.Gateways.Repositories.models.MaintenanceDBO;
import com.example.activity1.shared.domain.PageQuery;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SqlMaintenanceRepository implements MaintenanceRepository {
    private final DataSource dataSource;

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
                    MaintenanceDBO dbo = new MaintenanceDBO();
                    dbo.setIdMaintenance(resultSet.getString("maintenance_id"));
                    dbo.setStartDateTime(resultSet.getTimestamp("startDateTime").toLocalDateTime());
                    dbo.setStartDateTime(resultSet.getTimestamp("startDateTime").toLocalDateTime());
                    dbo.setDescription(resultSet.getString("description"));

                    MaintenanceService domainMaintenanceService = dbo.toDomain();
                    result.add(domainMaintenanceService);
                }
            resultSet.close();

            return result;

        } catch (SQLException exception){
            throw new RuntimeException("Error querying database", exception);
        }
    }

    @Override
    public Optional<MaintenanceService> get(MaintenanceId maintenanceId) {
        return Optional.empty();
    }

    @Override
    public void store(MaintenanceService maintenanceService) {

    }
}
