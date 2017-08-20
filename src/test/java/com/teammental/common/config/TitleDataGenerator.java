package com.teammental.common.config;

import com.teammental.common.dal.entity.Title;
import com.teammental.mebuilder.GenericBuilder;
import com.teammental.memapper.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TitleDataGenerator {

  /**
   * Generates a Title object with random data.
   * @return title object
   */
  public static Title prepareRandomTitle() {
    Random random = new Random();
    int id = random.nextInt();
    String name = StringUtil.generateRandomString(10);
    boolean isBoard = random.nextBoolean();
    int clientId = random.nextInt();

    Title title = GenericBuilder.of(Title::new)
        .with(Title::setId, id)
        .with(Title::setName, name)
        .with(Title::setBoard, isBoard)
        .with(Title::setClientId, clientId)
        .build();

    return title;
  }

  /**
   * Generates a List of Title objects with random data.
   * @param size list size
   * @return list of title
   */
  public static List<Title> prepateRandomListOfTitle(int size) {
    List<Title> titles = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      Title title = prepareRandomTitle();
      titles.add(title);
    }
    return titles;
  }
}
