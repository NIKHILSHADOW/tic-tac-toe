package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HumanPlayer extends Player {

    private String name;
    private String email;
    private Byte[] profileImg;

    @Override
    public void makeMove() {

    }

}
