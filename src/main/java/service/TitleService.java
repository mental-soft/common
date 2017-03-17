package service;

import dto.TitleListDto;

import java.util.List;

/**
 * Created by Nyomoto on 18.2.2017.
 */
public interface TitleService {

  List<TitleListDto> getAll();

  TitleListDto getById(int titleId) throws Exception;

  void deleteById(int titleId);

  int saveOrUpdate(TitleListDto dto) throws Exception;

}
