package com.example.models;

import com.example.strategies.GameStrategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BotPlayer extends Player {

    private GameLevel gameLevel;
    private GameStrategy gameStrategy;

    @Override
    public void makeMove() {

    }

}
