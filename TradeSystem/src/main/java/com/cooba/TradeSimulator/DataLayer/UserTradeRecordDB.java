package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Entity.UserTradeRecord;
import com.cooba.TradeSimulator.Mapper.UserTradeRecordDynamicSqlSupport;
import com.cooba.TradeSimulator.Mapper.UserTradeRecordMapper;
import com.cooba.TradeSimulator.Util.DateUtil;
import lombok.AllArgsConstructor;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
@AllArgsConstructor
public class UserTradeRecordDB {
    private final UserTradeRecordMapper userTradeRecordMapper;

    public boolean insert(UserTradeRecord userTradeRecord) {
        return userTradeRecordMapper.insert(userTradeRecord) == 1;
    }

    public boolean update(UserTradeRecord userTradeRecord) {
        if (userTradeRecord.getStatus() == 1) {
            UpdateStatementProvider query = SqlBuilder.update(UserTradeRecordDynamicSqlSupport.userTradeRecord)
                    .set(UserTradeRecordDynamicSqlSupport.status).equalTo(1)
                    .set(UserTradeRecordDynamicSqlSupport.price).equalTo(userTradeRecord.getPrice())
                    .set(UserTradeRecordDynamicSqlSupport.money).equalTo(userTradeRecord.getMoney())
                    .set(UserTradeRecordDynamicSqlSupport.stockDate).equalTo(userTradeRecord.getStockDate())
                    .set(UserTradeRecordDynamicSqlSupport.updatedTime).equalTo(DateUtil.now())
                    .where(UserTradeRecordDynamicSqlSupport.accountId, isEqualTo(userTradeRecord.getAccountId()))
                    .and(UserTradeRecordDynamicSqlSupport.billId, isEqualTo(userTradeRecord.getBillId()))
                    .build().render(RenderingStrategies.MYBATIS3);
            return userTradeRecordMapper.update(query) == 1;
        }
        if (userTradeRecord.getStatus() == -1) {
            UpdateStatementProvider query = SqlBuilder.update(UserTradeRecordDynamicSqlSupport.userTradeRecord)
                    .set(UserTradeRecordDynamicSqlSupport.status).equalTo(-1)
                    .set(UserTradeRecordDynamicSqlSupport.errMsg).equalTo(userTradeRecord.getErrMsg())
                    .set(UserTradeRecordDynamicSqlSupport.updatedTime).equalTo(DateUtil.now())
                    .where(UserTradeRecordDynamicSqlSupport.accountId, isEqualTo(userTradeRecord.getAccountId()))
                    .and(UserTradeRecordDynamicSqlSupport.billId, isEqualTo(userTradeRecord.getBillId()))
                    .build().render(RenderingStrategies.MYBATIS3);
            return userTradeRecordMapper.update(query) == 1;
        }
        return false;
    }

    public Optional<UserTradeRecord> selectByBillId(String billId) {
        SelectStatementProvider query = SqlBuilder.select(UserTradeRecordMapper.selectList)
                .from(UserTradeRecordDynamicSqlSupport.userTradeRecord)
                .where(UserTradeRecordDynamicSqlSupport.billId, isEqualTo(billId))
                .build().render(RenderingStrategies.MYBATIS3);
        return userTradeRecordMapper.selectOne(query);
    }
}
