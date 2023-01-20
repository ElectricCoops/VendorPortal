package pwr.lcec.vendorportal.session;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import pwr.lcec.vendorportal.interfaces.StreetLightLocal;

@Stateless(name = "StreetLightServiceBean", mappedName = "ejb/lcec/StreetLightProcessorBean", description = "StreetLightProcessorBean Business Facade")
@Local({ StreetLightLocal.class })
@Interceptors({ pwr.lcec.vendorportal.helper.LoggingInterceptor.class })
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class StreetLightSession implements StreetLightLocal {

}
