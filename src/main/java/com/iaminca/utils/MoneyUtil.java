package com.iaminca.utils;

import com.iaminca.common.ErrorCode;
import com.iaminca.exception.BusinessException;
import org.springframework.util.ObjectUtils;

import java.text.DecimalFormat;

public class MoneyUtil {


    public static Double getYuanUniteMoney(Integer money) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formattedValue = decimalFormat.format(money / 100.00);
        return Double.parseDouble(formattedValue);
    }

    public static Integer getCentUniteMoney(Double money) {
        if(ObjectUtils.isEmpty(money) || money < 1){
            throw new BusinessException(ErrorCode.RECHARGE_BALANCE_ERROR);
        }
       Double moneyDouble = money * 100;
        return moneyDouble.intValue();
    }

}
