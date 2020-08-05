/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Helper.jdbcHelper;
import Model.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class NhanVienDAO {
    public void insert(NhanVien model) {
        try {
            String sql = "INSERT INTO NhanVien (maNV, tenNV, gioiTinh, diaChi, dienThoai,vaiTro,matKhau)VALUES (?, ?, ?, ?, ?,?,?)";
            jdbcHelper.executeUpdate(sql,
                    model.getMaNV(),
                    model.getTenNV(),
                     model.getGioiTinh(),
                     model.getDiaChi(),
                     model.getDienThoai(),
                     model.isVaiTro(),
                     model.getMatKhau());
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET tenNV=?, gioiTinh=?,diaChi=?, dienThoai=?, vaiTro=?,matKhau=? WHERE maNV=?";
        jdbcHelper.executeUpdate(sql,
                     model.getTenNV(),
                     model.getGioiTinh(),
                                           model.getDiaChi(),
                     model.getDienThoai(),
                    
                     model.isVaiTro(),
                     model.getMatKhau(),
                     model.getMaNV()
        );
                   
    }

    public void delete(String id) {
        String sql = "DELETE FROM NhanVien WHERE maNV=?";
        jdbcHelper.executeUpdate(sql, id);
    }

    public List<NhanVien> select() {
        String sql = "SELECT * FROM NhanVien";
        return select(sql);
    }

//    public List<QuanTri> selectByKeyword(String keyword) {
//        String sql = "SELECT * FROM QuanTri WHERE HoTen LIKE ?";
//        return select(sql, "%" + keyword + "%");
//    }

//    public List<QuanTri> selectByCourse(Integer makh) {
//        String sql = "SELECT * FROM QuanTri WHERE MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
//        return select(sql, makh);
//    }

    public NhanVien findById(String maNV) {
        String sql = "SELECT * FROM NhanVien WHERE maNV=?";
        List<NhanVien> list = select(sql,maNV);
        return list.size() > 0 ? list.get(0) : null;
    }
     public NhanVien findByName(String tenNV) {
        String sql = "SELECT * FROM NhanVien WHERE tenNV=?";
        List<NhanVien> list = select(sql,tenNV);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<NhanVien> select(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NhanVien model = readFromResultSet(rs);
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

    private NhanVien readFromResultSet(ResultSet rs) throws SQLException {
        NhanVien model = new NhanVien();
        model.setMaNV(rs.getString("maNV"));
        model.setTenNV(rs.getString("tenNV"));
         model.setGioiTinh(rs.getString("gioiTinh"));
        model.setDiaChi(rs.getString("diaChi"));
        model.setDienThoai(rs.getString("dienThoai"));
     
         model.setVaiTro(rs.getBoolean("vaiTro"));
       model.setMatKhau(rs.getString("matKhau"));
       
        return model;
    }

}
