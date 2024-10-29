# Arkanoid Game

## Overview

This project is a Java-based implementation of the classic Arkanoid game done as part of OOP course. It features multiple levels of increasing difficulty, each with a variety of brick patterns. The goal is to break all the bricks while preventing the ball from falling.

## Game Features

- **Paddle Movement**: Control the paddle using the left and right arrow keys.
- **Ball Mechanics**: Realistic ball physics, including bouncing off the paddle and bricks.
- **Brick Variations**: Different types of bricks that require varying numbers of hits to break.
- **Score System**: Tracks the player's score as they progress through each level.
- **Game States**: Start, Pause, and End screens to provide better control and game flow.

## Screenshots

![image](https://github.com/user-attachments/assets/5cce8e6b-6b02-4c1c-890b-16682da3247e)
![image](https://github.com/user-attachments/assets/2ed60156-1cc8-4c0d-9c25-0157fe8292e0)

## Compile and Run

This project includes an Ant build file, making it easy to compile and run the game.

1. **Compile the code** using this Ant command:
   ```
   ant compile
   ```

2. **Run the game** with:
   ```
   ant run
   ```

You can also specify the sequence of levels you'd like to play by passing them as an argument. For example:
   ```
   ant -Dargs="1 3 2 1 2 3 1" run
   ```

## Game Controls

- **Left Arrow**: Move the paddle to the left.
- **Right Arrow**: Move the paddle to the right.
- **Space**: Exit the game after winning or losing.
- **P**: Pause the game.
