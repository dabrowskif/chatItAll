package models;

import controllers.chatController;
import controllers.clientController;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import myclasses.Friend;

import java.io.IOException;
import java.util.*;

public class clientModel {

    private Map<Integer, chatController> chatWindowsMap;
    private List<Integer> chatWindowsToCloseList;
    private clientController clientController;

    public clientModel(clientController clientController) {
        this.clientController = clientController;
        initializeComponents();

        addFriendsToObservableList();
        setAndShowListView();

    }

    private void initializeComponents() {
        chatWindowsMap = new HashMap<>();
        chatWindowsToCloseList = new ArrayList<>();

    }


    //adding static friends for testing purposes
    public void addFriendsToObservableList() {
        clientController.friendsObservableList.add(new Friend(1, "Filip", "Dąbrowski", Friend.UserStatusEnum.ON));
        clientController.friendsObservableList.add(new Friend(2, "Simba", "Minimakaberka", Friend.UserStatusEnum.ON));
        clientController.friendsObservableList.add(new Friend(3, "Grupa", "Trubadurów", Friend.UserStatusEnum.BRB));
        clientController.friendsObservableList.add(new Friend(4, "Maciej", "Lekowski", Friend.UserStatusEnum.OFF));
        clientController.friendsObservableList.add(new Friend(5, "Weronika", "Kozłowska <3", Friend.UserStatusEnum.ON));
        clientController.friendsObservableList.add(new Friend(6, "Błażej", "Kowalski", Friend.UserStatusEnum.OFF));
        clientController.friendsObservableList.add(new Friend(7, "Justyn", "Lekkomyślny", Friend.UserStatusEnum.INVIS));
        clientController.friendsObservableList.add(new Friend(8, "Zdzisław", "Stary", Friend.UserStatusEnum.OFF));
        clientController.friendsObservableList.add(new Friend(9, "Ryszard", "Ryszewski", Friend.UserStatusEnum.OFF));
        clientController.friendsObservableList.add(new Friend(10, "Mirosław", "Tegoroczny", Friend.UserStatusEnum.OFF));
        sortFriendsObservableList();
    }

    private static class friendCell extends ListCell<Friend> {
        @Override
        public void updateItem(Friend item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(createStatusImage(item.getStatus()));
                setText(item.getImie() + " " + item.getNazwisko());
            }
        }
    }

    public static ImageView createStatusImage(Friend.UserStatusEnum status) {
        ImageView statusImage = new ImageView();
        switch(status) {
            case ON:
                statusImage.setImage(new Image("/img/online.png"));
                break;
            case OFF:
            case INVIS:
                statusImage.setImage(new Image("/img/offline.png"));
                break;
            case BRB:
                statusImage.setImage(new Image("/img/brb.png"));
                break;
            default:
                break;
        }
        statusImage.setFitHeight(25);
        statusImage.setFitWidth(25);
        return statusImage;
    }

    private void sortFriendsObservableList() {
        FXCollections.sort(clientController.friendsObservableList, new StatusComparator());
    }

    static class StatusComparator implements Comparator<Friend> {
        public int compare(Friend f1, Friend f2) {
            return f1.getStatus().compareTo(f2.getStatus());
        }
    }

    public void setAndShowListView() {
        clientController.friendsListView.setItems(clientController.friendsObservableList);
        clientController.friendsListView.setCellFactory(list -> new friendCell());

    }

    public void openChatWindowWithAnotherUser(Friend friend) {
        if(!checkIfChatWindowIsAlreadyOpenWithFriend(friend)) {
            try {
                chatController chatController = new chatController(this, friend);
                chatController.showStage();
                chatWindowsMap.put(friend.getId(), chatController);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }

    private boolean checkIfChatWindowIsAlreadyOpenWithFriend(Friend friend) {
        return chatWindowsMap.containsKey(friend.getId());
    }

    public void closeAllChatWindows() {
        getOpenedChatWindows();
        closeOpenedChatWindows();
    }

    private void getOpenedChatWindows() {
        for(Map.Entry<Integer, chatController> entry : chatWindowsMap.entrySet()) {
            chatWindowsToCloseList.add(entry.getKey());
        }
    }

    private void closeOpenedChatWindows() {
        for(Integer i : chatWindowsToCloseList) {
            chatWindowsMap.get(i).closeStage();
            chatWindowsMap.remove(i);
        }
        chatWindowsToCloseList.clear();
    }

    public void closeChatWindowForFriend(int userID) {
        chatWindowsMap.remove(userID);
    }

}
