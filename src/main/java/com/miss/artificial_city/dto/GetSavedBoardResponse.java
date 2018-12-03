package com.miss.artificial_city.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor(staticName = "of")
public class GetSavedBoardResponse {
    private BoardDto boardDto;
}
