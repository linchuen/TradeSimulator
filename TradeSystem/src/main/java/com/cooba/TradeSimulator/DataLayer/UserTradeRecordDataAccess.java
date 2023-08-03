package com.cooba.TradeSimulator.DataLayer;

import com.cooba.TradeSimulator.Entity.UserTradeRecord;
import com.cooba.TradeSimulator.Mapper.UserTradeRecordDynamicSqlSupport;
import com.cooba.TradeSimulator.Mapper.UserTradeRecordMapper;
import com.cooba.TradeSimulator.Util.DateUtil;
import lombok.AllArgsConstructor;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.stereotype.Service;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
@AllArgsConstructor
public class UserTradeRecordDataAccess {
    private final UserTradeRecordMapper userTradeRecordMapper;

    public boolean insert(UserTradeRecord userTradeRecord) {
        return userTradeRecordMapper.insert(userTradeRecord) == 1;
    }

    public boolean update(UserTradeRecord userTradeRecord) {
        UpdateStatementProvider query = SqlBuilder.update(UserTradeRecordDynamicSqlSupport.userTradeRecord)
                .set(UserTradeRecordDynamicSqlSupport.status).equalTo(userTradeRecord.getStatus())
                .set(UserTradeRecordDynamicSqlSupport.errMsg).equalTo(userTradeRecord.getErrMsg())
                .set(UserTradeRecordDynamicSqlSupport.updatedTime).equalTo(DateUtil.now())
                .where(UserTradeRecordDynamicSqlSupport.accountId, isEqualTo(userTradeRecord.getAccountId()))
                .and(UserTradeRecordDynamicSqlSupport.billId, isEqualTo(userTradeRecord.getBillId()))
                .build().render(RenderingStrategies.MYBATIS3);
        return userTradeRecordMapper.update(query) == 1;
    }
}