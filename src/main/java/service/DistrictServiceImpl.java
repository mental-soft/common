package service;

import dto.DistrictDto;
import entity.District;
import jpa.DistrictRepository;
import mapper.DistrictListDtoMapper;
import mapper.DtoToDistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by okan on 15.02.2017.
 */
@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    @Override
    public List<DistrictDto> getAllDistrict() {
        List<District> list = districtRepository.findAll();
        return DistrictListDtoMapper.mapEntitiesToDtos(list);
    }

    @Override
    public List<DistrictDto> getAllDistrictByCity(Integer City_Id) {
        List<District> list = districtRepository.findByCity_Id(City_Id);
        return DistrictListDtoMapper.mapEntitiesToDtos(list);
    }

    @Override
    public DistrictDto getDistrict(DistrictDto districtdto) {
        District district = districtRepository.findOne(districtdto.getId());
        return DistrictListDtoMapper.mapEntityToDto(district);
    }

    @Override
    public void insertDistrict(DistrictDto districtdto) {
        District district = DtoToDistrictMapper.mapDtoToEntity(districtdto);
        districtRepository.save(district);
    }

    @Override
    public void updateDistrict(DistrictDto districtdto) {
        District district = DtoToDistrictMapper.mapDtoToEntity(districtdto);
        districtRepository.save(district);
    }

    @Override
    public void deleteDistrict(DistrictDto districtdto) {
        districtRepository.delete(districtdto.getId());
    }
}
