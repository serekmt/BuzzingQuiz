package com.quiz.buzzquiz.Models;

/**
 * Created by m on 2015-05-29.
 */
public class AppDataSingleton {

    private static AppDataSingleton instance;

    public AppDataSingleton() {
    }

    public static AppDataSingleton getInstance()
    {
        if( instance == null )
        {
            instance = new AppDataSingleton();
        }
        return instance;
    }

    @Override
    protected void finalize() throws Throwable {
        clear();
        super.finalize();
    }

    public void clear()
    {
        if(currentPlayer != null)
            DeletePlayer(currentPlayer.Name);

        instance = null;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    private Player currentPlayer;
    public Room currentRoom;

    private void DeletePlayer(String name){

    }
}
