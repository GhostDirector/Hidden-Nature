package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

public class PrefHandler {

    private Preferences prefs;
    private Preferences globalPrefs;
    private Level level;
    private ObjectManager om;
    private Array<Entity>entities = new Array<Entity>();
    private Array<Entity>silhouettes = new Array<Entity>();
    private Array<Entity>originals = new Array<Entity>();

    private Entity tmpEnt;

    public Array<Entity> getEntities() {
        return entities;
    }

    public Array<Entity> getOriginals() {
        return originals;
    }

    public Array<Entity> getSilhouettes() {
        return silhouettes;
    }

    public PrefHandler(Level l){

        level = l;


        prefs = Gdx.app.getPreferences("savedLevel"+level.getLevelID());
        globalPrefs = Gdx.app.getPreferences("settings");
    }

    public void save(){
        Array<Entity> saveEntities = level.getEntities();
        Array<Entity> saveOriginals = level.getOriginals();
        Array<Entity> saveSilhouettes = level.getSilhouettes();

        String entity = "";
        String original = "";
        String silhouette = "";



        for(int i = 0; i < saveEntities.size; i++){
            entity += saveEntities.get(i).getButtonID() +
                            "::"+ saveEntities.get(i).getOriginal() +
                            "::"+ saveEntities.get(i).getX() +
                            "::"+ saveEntities.get(i).getY() +
                            "::"+ saveEntities.get(i).getScale() +
                            "::"+ String.valueOf(saveEntities.get(i).isFound()) + ":;:";

        }

        for(int i = 0; i < saveOriginals.size; i++){
            original += saveOriginals.get(i).getButtonID() +
                            "::"+ saveOriginals.get(i).getOriginal() +
                            "::"+ saveOriginals.get(i).getX() +
                            "::"+ saveOriginals.get(i).getY() +
                            "::"+ saveOriginals.get(i).getScale() +
                            "::"+ String.valueOf(saveOriginals.get(i).isFound()) + ":;:";
        }

        for(int i = 0; i < saveSilhouettes.size; i++){
            silhouette += saveSilhouettes.get(i).getButtonID() +
                            "::"+ saveSilhouettes.get(i).getOriginal() +
                            "::"+ saveSilhouettes.get(i).getX() +
                            "::"+ saveSilhouettes.get(i).getY() +
                            "::"+ saveSilhouettes.get(i).getScale() +
                            "::"+ String.valueOf(saveSilhouettes.get(i).isFound()) + ":;:";
        }

        prefs.putString("Entity", ""+entity);
        prefs.putString("Original", ""+original);
        prefs.putString("Silhouette", ""+silhouette);
        prefs.flush();
    }

    public boolean loadLevel() {
        boolean isLoad;
        try {
            prefs = Gdx.app.getPreferences("savedLevel"+level.getLevelID());

            boolean reset = globalPrefs.getBoolean("Reset", false);

            String entity = prefs.getString("Entity");
            String original = prefs.getString("Original");
            String silhouette = prefs.getString("Silhouette");



            if (reset){
                globalPrefs.putBoolean("Reset", false);
                globalPrefs.flush();
                isLoad = false;

            } else {

                String[] entitiesString = entity.split(":;:");
                String[] originalsString = original.split(":;:");
                String[] silhouettesString = silhouette.split(":;:");


                entities = getArrays(entitiesString);
                originals = getArrays(originalsString);
                silhouettes = getArrays(silhouettesString);

                isLoad = true;
            }


        } catch (Exception e){
            isLoad = false;

        }
        return isLoad;
    }

    public Array<Entity> getArrays(String[] longStrings) {
        Array<Entity>array = new Array<Entity>();

        for(int i = 0; i < longStrings.length; i++){
            String[] shortStrings = longStrings[i].split("::");

            String file = shortStrings[1];

            float x = Float.valueOf(shortStrings[2]);
            float y = Float.valueOf(shortStrings[3]);
            int ID = Integer.valueOf(shortStrings[0]);
            float scale = Float.valueOf(shortStrings[4]);

            boolean found = Boolean.valueOf(shortStrings[5]);


            tmpEnt = new Entity(file, x, y, ID, scale, found);

            array.add(tmpEnt);

        }

//        for (String str : longStrings) {
//            String[] shortStrings = str.split("::");
//
//            array.add(new Entity(shortStrings[1],
//                    Float.valueOf(shortStrings[2]),
//                    Float.valueOf(shortStrings[3]),
//                    Integer.valueOf(shortStrings[0]),
//                    Float.valueOf(shortStrings[4]),
//                    Boolean.valueOf(shortStrings[5])));
//
//        }

        return array;
    }
}

/*
-1::hirvi2.png::279.0::78.0::1.0::true
-2::orava2.png::90.0::360.0::1.0::false
-3::siili2.png::535.0::5.0::1.0::false
-4::tintti2.png::190.0::398.0::1.0::false
-1
hirvi2.png
279.0
78.0
-2
orava2.png
90.0
360.0
-3
siili2.png
535.0
5.0
-4
tintti2.png
190.0
398.0
-1::hirvi.png::0.0::0.0::1.0::false
-2::orava.png::0.0::0.0::1.0::false
-3::HEDGEHOG.png::0.0::0.0::0.15::false
-4::TIT.png::0.0::0.0::0.12::false
-1
hirvi.png
0.0
0.0
-2
orava.png
0.0
0.0
-3
HEDGEHOG.png
0.0
0.0
-4
TIT.png
0.0
0.0
-1::hirviSil.png::10.0::10.0::1.0::false
-2::oravaSil.png::150.0::10.0::1.0::false
-3::siiliSil.png::250.0::10.0::1.14::false
-4::tinttiSil.png::350.0::10.0::1.45::false
-1
hirviSil.png
10.0
10.0
-2
oravaSil.png
150.0
10.0
-3
siiliSil.png
250.0
10.0
-4
tinttiSil.png
350.0
10.0
new
new
new
new
new
new
new
new
new
new
new
new

─────────▄──────────────▄────
────────▌▒█───────────▄▀▒▌───
────────▌▒▒▀▄───────▄▀▒▒▒▐───
───────▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐───
─────▄▄▀▒▒▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐───
───▄▀▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀██▀▒▌───
──▐▒▒▒▄▄▄▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄▒▒▌──
──▌▒▒▐▄█▀▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐──
─▐▒▒▒▒▒▒▒▒▒▒▒▌██▀▒▒▒▒▒▒▒▒▀▄▌─
─▌▒▀▄██▄▒▒▒▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌─
─▌▀▐▄█▄█▌▄▒▀▒▒▒▒▒▒░░░░░░▒▒▒▐─
▐▒▀▐▀▐▀▒▒▄▄▒▄▒▒▒▒▒░░░░░░▒▒▒▒▌
▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒░░░░░░▒▒▒▐─
─▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌─
─▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▐──
──▀▄▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▄▒▒▒▒▌──
────▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀───
───▐▀▒▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀─────
──▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀▀────────


<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
<entry key="Entity-3">-3::siili2.png::535.0::5.0::false:;:</entry>
<entry key="Entity-2">-2::orava2.png::90.0::360.0::true:;:</entry>
<entry key="Entity-1">-1::hirvi2.png::279.0::78.0::true:;:</entry>
<entry key="Entity">-1::hirvi2.png::279.0::78.0::true:;:-2::orava2.png::90.0::360.0::true:;:-3::siili2.png::535.0::5.0::true:;:-4::tintti2.png::190.0::398.0::true:;:</entry>
<entry key="pauseEntities-4">-4::TIT.png::0.0::0.0::false:;:</entry>
<entry key="pauseEntities-3">-3::HEDGEHOG.png::0.0::0.0::false:;:</entry>
<entry key="pauseEntities-2">-2::orava.png::0.0::0.0::false:;:</entry>
<entry key="pauseEntities-1">-1::hirvi.png::0.0::0.0::false:;:</entry>
<entry key="originals-4">-4::tinttiSil.png::350.0::10.0::false:;:</entry>
<entry key="originals-3">-3::siiliSil.png::250.0::10.0::false:;:</entry>
<entry key="originals-2">-2::oravaSil.png::150.0::10.0::false:;:</entry>
<entry key="originals-1">-1::hirviSil.png::10.0::10.0::false:;:</entry>
<entry key="Entity-4">-4::tintti2.png::190.0::398.0::false:;:</entry>
</properties>


 */

