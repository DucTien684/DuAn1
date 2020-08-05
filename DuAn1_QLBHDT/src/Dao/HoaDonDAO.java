/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Helper.jdbcHelper;
import Model.Hoadon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class HoaDonDAO {
      jdbcHelper jdbc=new jdbcHelper();
    public void insert(Hoadon model) {
        String sql = "INSERT INTO HoaDon (maHD,  maKH, maNV,ngayBan,tongTien) VALUES (?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
                model.getMaHD(),
                model.getMaKH(),
               
                model.getMaNV(),
                model.getNgayBan(),
                model.getTongTien());
              
               
    }

    public void update(Hoadon model) {
        String sql = "UPDATE HoaDon SET  maKH=?, maNV=?, ngayBan=?, tongTien=? WHERE maHD=?";
   jdbcHelper.executeUpdate(sql,
               
                model.getMaKH(),
                model.getMaNV(),
                model.getNgayBan(),
                model.getTongTien(),
              
               model.getMaHD());
    }

    public void delete(String maHD) {
        String sql = "DELETE FROM HoaDon WHERE maHD=?";
        jdbcHelper.executeUpdate(sql, maHD);
    }

    public List<Hoadon> select() {
        String sql = "SELECT * FROM HoaDon";
        return select(sql);
    }

    public Hoadon findById(String maHD) {
        String sql = "SELECT * FROM HoaDon WHERE maHD=?";
        List<Hoadon> list = select(sql, maHD);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<Hoadon> select(String sql, Object... args) {
        List<Hoadon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Hoadon model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private Hoadon readFromResultSet(ResultSet rs) throws SQLException {
        Hoadon model = new Hoadon();
        model.setMaHD(rs.getString("maHD"));
     
         model.setMaKH(rs.getString("manKH"));
         model.setMaNV(rs.getString("maNV"));
        model.setNgayBan(rs.getString("ngayBan"));
      model.setTongTien(rs.getDouble("tongTien"));
        
        return model;
    } 

   
}
