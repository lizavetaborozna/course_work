package converter;


import model.Card;
import dto.CardDTO;

import java.util.ArrayList;
import java.util.List;

public class CardConverter {
    public CardDTO convertCardToCardDUO(Card card) {
        return CardDTO.newBuilder()
                .number(card.getNumber())
                .month(card.getMonth())
                .year(card.getYear())
                .description(card.getDescription())
                .build();
    }

    public List<CardDTO> convertListCardToListCardDTO(List<Card> listCards) {
        List<CardDTO> cardDTOList = new ArrayList<>();
        for (Card card : listCards) {
            cardDTOList.add(convertCardToCardDUO(card));
        }
        return cardDTOList;
    }

    public Card convertCardDTOToCard(CardDTO cardDTO) {
        return Card.newBuilder()
                .number(cardDTO.getNumber())
                .month(cardDTO.getMonth())
                .year(cardDTO.getYear())
                .description(cardDTO.getDescription())
                .build();
    }
}
