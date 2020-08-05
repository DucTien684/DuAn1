/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Helper.jdbcHelper;
import Model.SanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class SanPhamDAO {
    public void insert(SanPham model){
        String sql="INSERT INTO SanPham (MaSP,MaNCC, TenSP, SoLuong, DonGiaNhap, DonGiaXuat, AnhSP) VALUES (?,?, ?, ?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql,
        model.getMaSP(),
   model.getMaNCC(),
        model.getTenSP(),
        
        model.getSoLuong(),
        model.getDonGiaNhap(),
        model.getDonGiaXuat(),
        model.getAnhSP());
}
public void update(SanPham model){
        String sql="UPDATE SanPham SET MaNCC=?,TenSP=?, SoLuong=?, DonGiaNhap=?, DonGiaXuat=?, AnhSP=? WHERE MaSP=?";
        jdbcHelper.executeUpdate(sql,
              model.getMaNCC(),
        model.getTenSP(),
        model.getSoLuong(),
        model.getDonGiaNhap(),
        model.getDonGiaXuat(),
        model.getAnhSP(),
        model.getMaSP());
}
public void delete(String MaSP){
       String sql="DELETE FROM SanPham WHERE MaSP=?";
       jdbcHelper.executeUpdate(sql, MaSP);
}
public List<SanPham> select(){
String sql="SELECT * FROM SanPham";
return select(sql);
}
 public void tim(){
          String sql="SELECT * FROM SanPham";
      };
public SanPham findById(String MaSP){
String sql="SELECT * FROM SanPham WHERE MaSP=?";
List<SanPham> list = select(sql, MaSP);
return list.size() > 0 ? list.get(0) : null;
}
private List<SanPham> select(String sql, Object...args){
List<SanPham> list=new ArrayList<>();
try {
ResultSet rs = null;
try {
rs = jdbcHelper.executeQuery(sql, args);
while(rs.next()){
SanPham model=readFromResultSet(rs);
list.add(model);
}
}
finally{
rs.getStatement().getConnection().close();
}
}
catch (SQLException ex) {
throw  new RuntimeException(ex);
}
return list;
}
private SanPham readFromResultSet(ResultSet rs) throws SQLException{
SanPham model=new SanPham();
model.setMaSP(rs.getString("MaSP"));
model.setTenSP(rs.getString("TenSP"));
model.setSoLuong(rs.getInt("SoLuong"));
model.setDonGiaNhap(rs.getDouble("DonGiaNhap"));
model.setDonGiaXuat(rs.getDouble("DonGiaXuat"));
model.setAnhSP(rs.getString("AnhSP"));
model.setMaNCC(rs.getString("MaNCC"));
return model;
}
}
