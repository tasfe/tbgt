package tbgt.service;

import tbgt.domain.ExpressCode;
import tbgt.persistence.ExpressCodeMapper;
import tbgt.web.criteria.ExpressCodeCriteria;

import java.math.BigDecimal;
import java.util.List;

public class ExpressCodeServiceImpl implements ExpressCodeService{
    private ExpressCodeMapper expressCodeMapper;


    public void setExpressCodeMapper(ExpressCodeMapper expressCodeMapper) {
        this.expressCodeMapper = expressCodeMapper;
    }

    @Override
    public List<ExpressCode> getExpressCode(ExpressCodeCriteria expressCodeCriteria) {
        List<ExpressCode> expressCodes = expressCodeMapper.getExpressCode(expressCodeCriteria.getProvince());
        BigDecimal weight = expressCodeCriteria.getWeight();
        if(weight !=null){
            //todo..
            for(ExpressCode expressCode : expressCodes){
                BigDecimal initWeight = new BigDecimal(expressCode.getInit_weight());
                if(weight.compareTo(initWeight) > 0){
                    BigDecimal diff = weight.subtract(initWeight);
                    expressCode.setCalcPrice(expressCode.getInit_price()+expressCode.getIncrease_price()*(diff.setScale(0,BigDecimal.ROUND_UP).intValue()));
                }else{
                    if(expressCode.getType().equals("韵达")&&expressCode.getLess1kg_price()!=0&&weight.setScale(0,BigDecimal.ROUND_UP).intValue()==1){
                        expressCode.setCalcPrice(expressCode.getLess1kg_price());
                    }else{
                        expressCode.setCalcPrice(expressCode.getInit_price());
                    }
                }
            }
        }
        return expressCodes;
    }
}
