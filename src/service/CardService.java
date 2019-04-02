package service;

import dto.CardDTO;

import java.util.List;

public interface CardService {
    List<CardDTO> getListCardForUser(String userName);

    void addCardForUser(CardDTO cardDTO, String userName);
}
