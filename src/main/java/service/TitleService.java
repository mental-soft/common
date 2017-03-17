package service;

import dto.TitleListDto;

import java.util.List;

/**
 * Created by Nyomoto on 18.2.2017.
 */
public interface TitleService {

  List<TitleListDto> getAll();

  TitleListDto getByID(int titleID) throws Exception;

  void deleteByID(int titleID);

  int saveOrUpdate(TitleListDto dto) throws Exception;

}
