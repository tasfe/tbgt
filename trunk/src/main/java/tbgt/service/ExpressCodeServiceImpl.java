package tbgt.service;

import tbgt.domain.ExpressCode;
import tbgt.persistence.ExpressCodeMapper;
import tbgt.web.criteria.ExpressCodeCriteria;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressCodeServiceImpl implements ExpressCodeService{
    private ExpressCodeMapper expressCodeMapper;


    public void setExpressCodeMapper(ExpressCodeMapper expressCodeMapper) {
        this.expressCodeMapper = expressCodeMapper;
    }

    @Override
    public List<ExpressCode> getExpressCodeList(ExpressCodeCriteria expressCodeCriteria) {
        List<ExpressCode> expressCodes = expressCodeMapper.getExpressCodeList(expressCodeCriteria.getProvince());
        BigDecimal weight = expressCodeCriteria.getWeight();
        if (weight != null) {
            for (ExpressCode expressCode : expressCodes) {
                expressCode.setCalcPrice(getExpressFee(expressCode, weight));

            }
        }
        return expressCodes;
    }

    @Override
    public ExpressCode getExpressCode(String province, String type) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("province",province);
        param.put("type",type);
        return expressCodeMapper.getExpressCode(param);
    }

    @Override
    public int getExpressFee(ExpressCode expressCode, BigDecimal weight) {
        BigDecimal initWeight = new BigDecimal(expressCode.getInit_weight());
        int fee;
        if (weight.compareTo(initWeight) > 0) {
            BigDecimal diff = weight.subtract(initWeight);
            fee = expressCode.getInit_price() + expressCode.getIncrease_price() * (diff.setScale(0, BigDecimal.ROUND_UP).intValue());
        } else {
            if (expressCode.getType().equals("韵达") && expressCode.getLess1kg_price() != 0 && weight.setScale(0, BigDecimal.ROUND_UP).intValue() == 1) {
                fee = expressCode.getLess1kg_price();
            } else {
                fee = expressCode.getInit_price();
            }
        }
        return fee;
    }
}
