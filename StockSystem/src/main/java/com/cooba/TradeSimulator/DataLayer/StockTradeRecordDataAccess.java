//package com.cooba.TradeSimulator.DataLayer;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.cooba.TradeSimulator.Entity.StockTradeRecord;
//import com.cooba.TradeSimulator.Request.StockTradeRecordReq;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class StockTradeRecordDataAccess implements BaseMapper<StockTradeRecord, StockTradeRecordReq> {
//    @Autowired
//    StockTradeRecordMapper stockTradeRecordMapper;
//
//    @Override
//    public List<StockTradeRecord> find(StockTradeRecordReq request) {
//        QueryWrapper<StockTradeRecord> query = new QueryWrapper<>();
//        query.eq("stockcode", request.getStockcode()).eq("date", request.getDate());
//        return stockTradeRecordMapper.selectList(query);
//    }
//
//    @Override
//    public List<StockTradeRecord> findAll() {
//        return stockTradeRecordMapper.selectList(new QueryWrapper<>());
//    }
//
//    @Override
//    public boolean save(StockTradeRecord entity) {
//        return stockTradeRecordMapper.updateById(entity) == 1;
//    }
//
//    @Override
//    public boolean saveAll(List<StockTradeRecord> entities) {
//        return false;
//    }
//
//    @Override
//    public boolean insert(StockTradeRecord entity) {
//        return stockTradeRecordMapper.insert(entity) == 1;
//    }
//
//    @Override
//    public boolean insertAll(List<StockTradeRecord> entities) {
//        return false;
//    }
//
//    @Override
//    public boolean delete(StockTradeRecordReq request) {
//        return false;
//    }
//
//    @Override
//    public boolean deleteAll(List<StockTradeRecord> entities) {
//        return false;
//    }
//}
