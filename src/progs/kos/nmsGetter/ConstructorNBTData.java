package progs.kos.nmsGetter;

import java.util.ArrayList;
import java.util.Map;

public class ConstructorNBTData {
    public String data;
    public int dataInt, power, type, effect;
    public float floatValue;
    public ArrayList<?> arrayData;
    public Map<String, ConstructorNBTData> mapData;
    public int[] mainColor, fadeColor;

    public ConstructorNBTData(Map<String, ConstructorNBTData> mapData) {
        this.mapData = mapData;
    }

    public ConstructorNBTData(String data) {
        this.data = data;
    }

    public ConstructorNBTData(float floatValue) {
        this.floatValue = floatValue;
    }

    public ConstructorNBTData(int dataInt) {
        this.dataInt = dataInt;
    }

    public ConstructorNBTData(ArrayList<?> arrayData) {
        this.arrayData = arrayData;
    }

    public ConstructorNBTData(int[] mainColor, int[] fadeColor, int type, int power, int effect) {
        this.mainColor = mainColor;
        this.fadeColor = fadeColor;
        this.power = power;
        this.type = type;
        this.effect = effect;
    }
    public ConstructorNBTData(int[] mainColor) {
        this.mainColor = mainColor;
    }
}
