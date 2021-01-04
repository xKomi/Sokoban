
/**
 * Klasa sprawdzaja kolizje miedzy obiektami klasy Element.
 */
public class Collision {
    /**
     * Obiekt klasy Level dla ktorego sprawdzane sa kolizje.
     */
    static private Level level;

    /**
     * Konstrukotr klasy Collision.
     * @param level obiekt klasy Level.
     */
    public Collision(Level level) {
        Collision.level = level;
    }

    /**
     * Metoda sprawdzajaca kolizje elementu ze scianami.
     * @param element element dla ktorego sprawdzane sa kolizje.
     * @param direction obiekt klasy String informujacy z ktorej strony zachodzi kolizja (lewa, prawa, gora, dol).
     * @return metoda zwraca true lub false w zaleznosci od tego czy zaszla kolizja.
     */
    public static boolean checkWallCollision(Element element, String direction) {
        switch (direction) {
            case "LEFT":
                for (int i = 0; i < level.Walls.size(); i++) {
                    if (element.isLeftCollision(level.Walls.get(i))) {
                        System.out.println("Lewa");
                        return true;
                    }
                }
                return false;
            case "RIGHT":
                for (int i = 0; i < level.Walls.size(); i++) {
                    if (element.isRightCollision(level.Walls.get(i))) {
                        System.out.println("Prawa");
                        return true;
                    }
                }
                return false;
            case "UP":
                for (int i = 0; i < level.Walls.size(); i++) {
                    if (element.isUpperCollision(level.Walls.get(i))) {
                        System.out.println("Gora");
                        return true;
                    }
                }
                return false;
            case "DOWN":
                for (int i = 0; i < level.Walls.size(); i++) {
                    if (element.isBottomCollision(level.Walls.get(i))) {
                        System.out.println("Dol");
                        return true;
                    }
                }
                return false;
            default:
                break;
        }
        return false;
    }

    /**
     * Metoda sprawdzajaca kolizje dla obiektow klasy Box.
     * @param direction obiekt klasy String informujacy z ktorej strony zachodzi kolizja.
     * @return true lub false w zaleznosci od tego czy zachodzi kolizja.
     */
    public static boolean checkBoxCollision(String direction) {
        switch (direction) {
            case "LEFT":
                for (int i = 0; i < level.Boxes.size(); i++) {
                    Box box1 = level.Boxes.get(i);
                    if (level.player.isLeftCollision(box1)) {
                        for (int j = 0; j < level.Boxes.size(); j++) {
                            Box box2 = level.Boxes.get(j);
                            if (!box1.equals(box2)) {
                                if (box1.isLeftCollision(box2)) {
                                    return true;
                                }
                            }
                            if (checkWallCollision(box1, direction)) {
                                return true;
                            }
                            if (checkPortalCollision(box1, direction)){
                                return true;
                            }
                        }
                        box1.moveBox(direction);
                        level.isCompleted();
                    }
                }
                return false;
            case "RIGHT":
                for (int i = 0; i < level.Boxes.size(); i++) {
                    Box box1 = level.Boxes.get(i);
                    if (level.player.isRightCollision(box1)) {
                        for (int j = 0; j < level.Boxes.size(); j++) {
                            Box box2 = level.Boxes.get(j);
                            if (!box1.equals(box2)) {
                                if (box1.isRightCollision(box2)) {
                                    return true;
                                }
                            }
                            if (checkWallCollision(box1, direction)) {
                                return true;
                            }
                            if (checkPortalCollision(box1, direction)){
                                return true;
                            }
                        }
                        box1.moveBox(direction);
                        level.isCompleted();
                    }
                }
                return false;
            case "UP":
                for (int i = 0; i < level.Boxes.size(); i++) {
                    Box box1 = level.Boxes.get(i);
                    if (level.player.isUpperCollision(box1)) {
                        for (int j = 0; j < level.Boxes.size(); j++) {
                            Box box2 = level.Boxes.get(j);
                            if (!box1.equals(box2)) {
                                if (box1.isUpperCollision(box2)) {
                                    return true;
                                }
                            }
                            if (checkWallCollision(box1, direction)) {
                                return true;
                            }
                            if (checkPortalCollision(box1, direction)){
                                return true;
                            }
                        }
                        box1.moveBox(direction);
                        level.isCompleted();
                    }
                }
                return false;
            case "DOWN":
                for (int i = 0; i < level.Boxes.size(); i++) {
                    Box box1 = level.Boxes.get(i);
                    if (level.player.isBottomCollision(box1)) {
                        for (int j = 0; j < level.Boxes.size(); j++) {
                            Box box2 = level.Boxes.get(j);
                            if (!box1.equals(box2)) {
                                if (box1.isBottomCollision(box2)) {
                                    return true;
                                }
                            }
                            if (checkWallCollision(box1, direction)) {
                                return true;
                            }
                            if (checkPortalCollision(box1, direction)){
                                return true;
                            }
                        }
                        box1.moveBox(direction);
                        level.isCompleted();
                    }
                }
                return false;
            default:
                break;
        }
        return false;
    }

    /**
     * Metoda sprawdzajaca kolizje elementu z portalem.
     * @param element element dla ktorego sprawdzana jest kolizja.
     * @param direction obiekt klasy String informujacy z ktorej strony zachodzi kolizja.
     * @return true lub false w zaleznosci od tego czy zachodzi kolizja.
     */
    public static boolean checkPortalCollision(Element element, String direction) {
        switch (direction) {
            case "LEFT":
                for (int i = 0; i < level.Portals.size(); i++) {
                    if (element.isLeftCollision(level.Portals.get(i))) {
                        if (level.Portals.get(i).getDirection() == direction) {
                            System.out.println("Lewa portal");
                            if (i + 1 == level.Portals.size()) {
                                level.Portals.get(i).teleport(level.Portals.get(0), element);
                            } else {
                                level.Portals.get(i).teleport(level.Portals.get(i + 1), element);
                            }
                        }
                        return true;
                    }
                }
                return false;
            case "RIGHT":
                for (int i = 0; i < level.Portals.size(); i++) {
                    if (element.isRightCollision(level.Portals.get(i))) {
                        if (level.Portals.get(i).getDirection() == direction) {
                            System.out.println("Prawa portal");
                            if (i + 1 == level.Portals.size()) {
                                level.Portals.get(i).teleport(level.Portals.get(0), element);
                            } else {
                                level.Portals.get(i).teleport(level.Portals.get(i + 1), element);
                            }
                        }
                        return true;
                    }
                }
                return false;
            case "UP":
                for (int i = 0; i < level.Portals.size(); i++) {
                    if (element.isUpperCollision(level.Portals.get(i))) {
                        if (level.Portals.get(i).getDirection() == direction) {
                            System.out.println("Gora portal");
                            if (i + 1 == level.Portals.size()) {
                                level.Portals.get(i).teleport(level.Portals.get(0), element);
                            } else {
                                level.Portals.get(i).teleport(level.Portals.get(i + 1), element);
                            }
                        }
                        return true;
                    }
                }
                return false;
            case "DOWN":
                for (int i = 0; i < level.Portals.size(); i++) {
                    if (element.isBottomCollision(level.Portals.get(i))) {
                        if (level.Portals.get(i).getDirection() == direction) {
                            System.out.println("Dol portal");
                            if (i + 1 == level.Portals.size()) {
                                level.Portals.get(i).teleport(level.Portals.get(0), element);
                            } else {
                                level.Portals.get(i).teleport(level.Portals.get(i + 1), element);
                            }
                        }
                        return true;
                    }
                }
                return false;
            default:
                break;
        }
        return false;
    }
}