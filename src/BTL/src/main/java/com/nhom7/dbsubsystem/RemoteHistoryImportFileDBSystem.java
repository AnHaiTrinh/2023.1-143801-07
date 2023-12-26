package com.nhom7.dbsubsystem;
import com.nhom7.entity.HistoryImportFile;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class RemoteHistoryImportFileDBSystem implements IHistoryImportFileDBSystem {
    @Override
    public List<HistoryImportFile> getAllHistoryImportFiles() {
        try{
            ArrayList<HistoryImportFile> historyImportFileArrayList = new ArrayList<>();
            Connection connection = DBSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM history_import order by day DESC"
            );
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                HistoryImportFile historyImportFile = new HistoryImportFile(
                        rs.getInt("id"),
                        rs.getDate("day").toLocalDate(),
                        rs.getTime("time").toLocalTime(),
                        rs.getInt("totalRecord")
                );
                historyImportFileArrayList.add(historyImportFile);
            }
            return historyImportFileArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean addHistoryImportFile(HistoryImportFile historyImportFile) {
        try {
            Connection connection = DBSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO history_import " +
                            "(day, time, totalRecord) " +
                            "VALUES (?, ?, ?)"
            );
            statement.setDate(1, Date.valueOf(historyImportFile.getDay()));
            statement.setTime(2, Time.valueOf(historyImportFile.getTime()));
            statement.setInt(3, historyImportFile.getTotalRecord());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private PreparedStatement constructFilterQuery(Connection connection, LocalDate day) throws SQLException {
        PreparedStatement statement;
        if(day == null) {
            statement = connection.prepareStatement(
                    "SELECT * FROM history_import"
            );
        } else {
            statement = connection.prepareStatement(
                    "SELECT * FROM history_import WHERE day = ?"
            );
            statement.setDate(1, Date.valueOf(day));
        }
        return statement;
    }

    @Override
    public List<HistoryImportFile> filterHistoryImportFileByDay(LocalDate day) {
        try {
            ArrayList<HistoryImportFile> historyImportFileArrayList = new ArrayList<>();
            Connection connection = DBSubsystemConnection.getConnection();
            PreparedStatement statement = constructFilterQuery(connection, day);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                HistoryImportFile historyImportFile = new HistoryImportFile(
                        rs.getDate("day").toLocalDate(),
                        rs.getTime("time").toLocalTime(),
                        rs.getInt("totalRecord")
                );
                historyImportFileArrayList.add(historyImportFile);
            }
            return historyImportFileArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
