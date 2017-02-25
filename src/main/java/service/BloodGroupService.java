package service;

import dto.BloodGroupListDto;

import java.util.List;

/**
 * Created by Nyomoto on 18.2.2017.
 */
public interface BloodGroupService {

    long bloodGroupCount();

    List<BloodGroupListDto> getAll();

    BloodGroupListDto getByID(int bloodGroupID);

    void deleteByID(int bloodGroupID);

    void saveOrUpdate(BloodGroupListDto dto);
}