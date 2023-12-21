package com.nhom7.dbsubsystem;
import com.nhom7.entity.HistoryImportFile;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RemoteHistoryImportFileDBSystem implements IAHistoryImportFileDBSystem{
    @Override
    public List<HistoryImportFile> getAllHistoryImportFiles() {
        try{
            ArrayList<HistoryImportFile> historyImportFileArrayList = new ArrayList<>();
            Connection connection = DBSubsystemConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM history_import"
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

}
