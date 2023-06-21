package controller.gamestructure;

import enumeration.Paths;
import enumeration.Textures;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import model.menugui.game.GameTile;
import model.menugui.game.TileSensor;

import java.util.HashMap;

public class GameImages {
    public static HashMap<String, Image> imageViews = new HashMap<>();

    public static void loadImages() {
        addTextures();
        addArabianTroop();
        addEuropeanTroops();
        addCursor();
        addRed();
        addBars();
    }

    public static void addTextures() {
        addEarth();
        addBeach();
        addBoulder();
        addGrass();
        addEarthAndSand();
        addOil();
        addMarsh();
        addOasisGrass();
        addIronTexture();
        addLargePond();
        addSmallPond();
        addRiver();
        addSea();
        addRockTexture();
        addThickGrass();
        addLowDepthWater();
    }

    public static void addCursor() {
        addSelectMove();
        addCanNot();
        addAttack();
    }

    public static void addBars() {
        addUnitBar();
        addMainBar();
    }

    public static void addArabianTroop() {
        addArabianSwordsman();
        addArabianArcher();
        addSlinger();
        addSlave();
        addAssassin();
        addFireThrower();
        addHorseArcher();
    }
    public static void addEuropeanTroops(){
        addKnight();
        addTunneler();
        addEngineer();
        addSwordsman();
        addArcher();
        addBlackmonk();
        addMaceman();
        addCrossbowman();
        addLord();
        addSpearman();
        addPikeman();
        addLadderman();
    }

    public static void addMainBar() {
        Image image = new Image(GameImages.class.getResource
                (Paths.BAR_IMAGES.getPath()).toExternalForm() + "bar.png");
        imageViews.put("bar", image);
    }

    public static void addUnitBar() {
        Image image = new Image(GameImages.class.getResource
                (Paths.BAR_IMAGES.getPath()).toExternalForm() + "unitBar.png");
        imageViews.put("unit bar", image);
    }

    public static void addSelectMove() {
        Image image = new Image(GameTile.class.getResource(Paths.GAME_IMAGES.getPath()).toExternalForm() + "cursor/selectMove.gif");
        imageViews.put("selectMove", image);
    }

    public static void addCanNot() {
        Image image = new Image(GameTile.class.getResource(Paths.GAME_IMAGES.getPath()).toExternalForm() + "cursor/cannot.gif");
        imageViews.put("cannot", image);
    }

    public static void addAttack() {
        Image image = new Image(GameTile.class.getResource(Paths.GAME_IMAGES.getPath()).toExternalForm() + "cursor/attack.gif");
        imageViews.put("attack", image);
    }

    //----------------------------------------------------------------------
    private static void addArabianSwordsman() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "arabianSwordsman/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("arabianSwordsman_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }

    private static void addArabianArcher() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "archerBow/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("archerBow_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }

    private static void addSlinger() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "slinger/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("slinger_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }

    private static void addHorseArcher() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "horseArcher/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("horseArcher_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }

    private static void addSlave() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "slave/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("slave_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }
    private static void addAssassin() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "archerBow/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("archerBow_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }private static void addFireThrower() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "fireThrower/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("fireThrower_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }

    //----------------------------------------------------------------------------------
    private static void addKnight() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "knight/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("knight_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }

    private static void addLadderman() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "ladderman/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("ladderman_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }

    private static void addCrossbowman() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "crossbowman/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("crossbowman_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }
    private static void addSpearman() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "spearman/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("spearman_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }
    private static void addLord() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "lord/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("lord_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }

    private static void addArcher() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "archer/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("archer_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }
    private static void addSwordsman() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "swordsman/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("swordsman_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }

    private static void addBlackmonk() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "blackmonk/Image" + counter + ".png");
                    imageViews.put("blackmonk_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }
    private static void addMaceman() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "maceman/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("maceman_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }
    private static void addPikeman() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "pikeman/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("pikeman_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }
    private static void addEngineer() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "engineer/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("engineer_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }
    private static void addTunneler() {
        String[] colors = {"blue", "red", "orange", "yellow", "grey", "purple", "skyBlue", "green"};
        for (int i = 0; i < 8; i++) {
            int counter = 1;
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    Image troop = new Image(GameTile.class.getResource(Paths.TROOP_IMAGES.getPath()).toExternalForm() +
                            "tunneler/" + colors[i] + "/Image" + counter + ".png");
                    imageViews.put("tunneler_" + colors[i] + "_" + counter, troop);
                    counter++;
                }
                counter += 8;
            }
        }
    }

    //--------------------------------------------------------------------------------
    public static void addEarth() {
        for (int i = 0; i < Textures.EARTH.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/earth/" + i + ".png").toExternalForm());
            imageViews.put("earth" + i, textureImg);
        }
    }

    public static void addBeach() {
        for (int i = 0; i < Textures.BEACH.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/beach/" + i + ".png").toExternalForm());
            imageViews.put("beach" + i, textureImg);
        }
    }

    public static void addBoulder() {
        for (int i = 0; i < Textures.BOULDER.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/boulder/" + i + ".png").toExternalForm());
            imageViews.put("boulder" + i, textureImg);
        }
    }

    public static void addEarthAndSand() {
        for (int i = 0; i < Textures.EARTH_AND_SAND.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/earthAndSand/" + i + ".png").toExternalForm());
            imageViews.put("earthAndSand" + i, textureImg);
        }
    }

    public static void addGrass() {
        for (int i = 0; i < Textures.GRASS.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/grass/" + i + ".png").toExternalForm());
            imageViews.put("grass" + i, textureImg);
        }
    }

    public static void addOil() {
        for (int i = 0; i < Textures.OIL.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/oil/" + i + ".png").toExternalForm());
            imageViews.put("oil" + i, textureImg);
        }
    }

    public static void addMarsh() {
        for (int i = 0; i < Textures.MARSH.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/marsh/" + i + ".png").toExternalForm());
            imageViews.put("marsh" + i, textureImg);
        }
    }

    public static void addLargePond() {
        for (int i = 0; i < Textures.LARGE_POND.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/largePond/" + i + ".png").toExternalForm());
            imageViews.put("largePond" + i, textureImg);
        }
    }

    public static void addSmallPond() {
        for (int i = 0; i < Textures.SMALL_POND.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/smallPond/" + i + ".png").toExternalForm());
            imageViews.put("smallPond" + i, textureImg);
        }
    }

    public static void addIronTexture() {
        for (int i = 0; i < Textures.IRON_TEXTURE.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/ironTexture/" + i + ".png").toExternalForm());
            imageViews.put("ironTexture" + i, textureImg);
        }
    }

    public static void addOasisGrass() {
        for (int i = 0; i < Textures.OASIS_GRASS.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/oasisGrass/" + i + ".png").toExternalForm());
            imageViews.put("oasisGrass" + i, textureImg);
        }
    }

    public static void addRiver() {
        for (int i = 0; i < Textures.RIVER.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/river/" + i + ".png").toExternalForm());
            imageViews.put("river" + i, textureImg);
        }
    }

    public static void addRockTexture() {
        for (int i = 0; i < Textures.ROCK_TEXTURE.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/rockTexture/" + i + ".png").toExternalForm());
            imageViews.put("rockTexture" + i, textureImg);
        }
    }

    public static void addSea() {
        for (int i = 0; i < Textures.SEA.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/sea/" + i + ".png").toExternalForm());
            imageViews.put("sea" + i, textureImg);
        }
    }

    public static void addThickGrass() {
        for (int i = 0; i < Textures.THICK_GRASS.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/thickGrass/" + i + ".png").toExternalForm());
            imageViews.put("thickGrass" + i, textureImg);
        }
    }

    public static void addLowDepthWater() {
        for (int i = 0; i < Textures.LOW_DEPTH_WATER.getCount(); i++) {
            Image textureImg = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath()
                    + "textures/lowDepthWater/" + i + ".png").toExternalForm());
            imageViews.put("lowDepthWater" + i, textureImg);
        }
    }

    public static void addRed() {
        Image image = new Image(GameTile.class.getResource(Paths.MAP_IMAGES.getPath() + "textures/red.png").toExternalForm());
        imageViews.put("red", image);
    }

    public static TileSensor getRedImageView(double x, double y, GameTile gameTile) {
        TileSensor tileSensor = new TileSensor(imageViews.get("red"), gameTile);
        tileSensor.setTranslateX(x);
        tileSensor.setTranslateY(y);
        tileSensor.setFitWidth(30);
        tileSensor.setFitHeight(18);
        tileSensor.setOpacity(0);
        return tileSensor;
    }
}
