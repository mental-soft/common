package service;

import dto.DistrictDto;
import dto.CityDto;
import java.util.List;
/**
 * Created by okan on 12.02.2017.
 */
public interface DistrictService {

    List<DistrictDto> getAllDistrictByCity(Integer cityID) ;

    List<DistrictDto> getAll();

    Boolean existDistrictByCity(Integer cityID);

    DistrictDto getByID(int districtID) throws Exception;

    void deleteByID(int districtID) throws Exception;

    int saveOrUpdate(DistrictDto dto) throws Exception;


}
