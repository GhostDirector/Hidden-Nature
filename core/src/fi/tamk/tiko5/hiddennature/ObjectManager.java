package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;



/**
 * Created by Jorix3 on 12.3.2017.
 */

public class ObjectManager {
    
    Array<Entity> entities;
    Array<Entity> pauseEntities;

    Array<Entity> originals;

    public ObjectManager(int id){
        entities = new Array<Entity>();
        originals = new Array<Entity>();
        pauseEntities = new Array<Entity>();

        switch (id){

            case 1:

                entities.add(new Entity("doge.png", 200f, 100f, -1));
                entities.add(new Entity("fatso.png", 300f, 200f, -2));
                entities.add(new Entity("doge.png", 250f, 150f, -3));
                entities.add(new Entity("doge.png", 350f, 250f, -4));

                originals.add(new Entity("doge.png", 200f, 100f, -1));
                originals.add(new Entity("fatso.png", 300f, 200f, -2));
                originals.add(new Entity("doge.png", 250f, 150f, -3));
                originals.add(new Entity("doge.png", 350f, 250f, -4));

                pauseEntities.add(new Entity("dogesilu.png", 10f, 10f, -1));
                pauseEntities.add(new Entity("fatsosilu.png", 50f, 10f, -2));
                pauseEntities.add(new Entity("dogesilu.png", 90f, 10f, -3));
                pauseEntities.add(new Entity("dogesilu.png", 130f, 10f, -4));
                break;



            case 2:
                entities.add(new Entity("doge.png", 300f, 200f, -1));

                pauseEntities.add(new Entity("dogesilu.png", 200f, 100f, -1));
                break;

            case 3:
                entities.add(new Entity("doge.png", 250f, 150f, -1));

                pauseEntities.add(new Entity("dogesilu.png", 200f, 100f, -1));
                break;



        }
    }

    public Array<Entity> getOriginals() {
        return originals;
    }

    Array<Texture> textures;

    public Array<Entity> getObjects() {
        return entities;
    }

    public Array<Entity> getPauseEntities() {
        return pauseEntities;
    }

}
