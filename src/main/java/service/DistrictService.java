package service;

import dto.DistrictDto;
import dto.CityDto;
import java.util.List;
/**
 * Created by okan on 12.02.2017.
 */
public interface DistrictService {

    List<DistrictDto> getAllDistrict();

    List<DistrictDto> getAllDistrictByCity(Integer City_Id) ;

    DistrictDto getDistrict(DistrictDto districtdto);

    void insertDistrict(DistrictDto districtdto);

    void updateDistrict(DistrictDto districtdto);

    void deleteDistrict(DistrictDto districtdto);

}
