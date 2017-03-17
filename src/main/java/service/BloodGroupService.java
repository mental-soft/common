package service;

import dto.BloodGroupListDto;

import java.util.List;

/**
 * Created by Nyomoto on 18.2.2017.
 */
public interface BloodGroupService {

    List<BloodGroupListDto> getAll();

    BloodGroupListDto getById(int bloodGroupId) throws Exception;

    void deleteById(int bloodGroupId);

    int saveOrUpdate(BloodGroupListDto dto) throws Exception;

}