/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Helper.jdbcHelper;
import Model.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class KhachHangDAO {
        jdbcHelper jdbc=new jdbcHelper();
    public void insert(KhachHang model) {
        String sql = "INSERT INTO KhachHang (maKH,  tenKH, gioiTinh,diaChi,dienThoai) VALUES (?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
                model.getMaKH(),
                model.getTenKH(),
               
                model.getGioiTinh(),
                model.getDiaChi(),
                model.getDienThoai());
              
               
    }

    public void update(KhachHang model) {
        String sql = "UPDATE KhachHang SET  tenKH=?, gioiTinh=?, diaChi=?, dienThoai=? WHERE maKH=?";
   jdbcHelper.executeUpdate(sql,
               
                model.getTenKH(),
                model.getGioiTinh(),
                model.getDiaChi(),
                model.getDienThoai(),
              
               model.getMaKH());
    }

    public void delete(String maKH) {
        String sql = "DELETE FROM KhachHang WHERE maKH=?";
        jdbcHelper.executeUpdate(sql, maKH);
    }

    public List<KhachHang> select() {
        String sql = "SELECT * FROM KhachHang";
        return select(sql);
    }

    public KhachHang findById(String maKH) {
        String sql = "SELECT * FROM KhachHang WHERE maKH=?";
        List<KhachHang> list = select(sql, maKH);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<KhachHang> select(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    KhachHang model = readFromResultSet(rs);
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

    private KhachHang readFromResultSet(ResultSet rs) throws SQLException {
        KhachHang model = new KhachHang();
        model.setMaKH(rs.getString("maKH"));
     
         model.setTenKH(rs.getString("tenKH"));
         model.setGioiTinh(rs.getString("gioiTinh"));
        model.setDiaChi(rs.getString("diaChi"));
        model.setDienThoai(rs.getString("dienThoai"));
        
        return model;
    } 

   
}
