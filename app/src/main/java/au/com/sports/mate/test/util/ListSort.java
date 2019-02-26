package au.com.sports.mate.test.util;

import java.util.*;

public class ListSort {

    static String[] orderList = {"l","c","s"};
    static List<String> order = new ArrayList();

    public static void main(String[] args) {
// write your code here
        order =Arrays.asList(orderList);
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Hit", "l"));
        users.add(new User(2, "Pit", "c"));
        users.add(new User(1, "Tit", "s"));
        users.add(new User(1, "Jit", "s"));
        users.add(new User(1, "Fit", "c"));
        users.add(new User(1, "Git", "c"));
        users.add(new User(1, "Qit", "l"));

        sort(users);
        for(User user:users) {
            System.out.println(user);
        }
    }

    static class User {
        int id;
        String name;
        String state;

        public User(int id, String name, String state) {
            this.id = id;
            this.state = state;
            this.name = name;
        }

        @Override
        public String toString() {
            return "id "+ id + "name " + name + "state "+ state;
        }
    }



    static public void sort(List<User> users) {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return order.indexOf(o1.state)>order.indexOf(o2.state)?1:-1;
            }
        });
    }
}