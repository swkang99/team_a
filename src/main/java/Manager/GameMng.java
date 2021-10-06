package Manager;

public class GameMng {

    private GameMng() {};

    private static class gameMng {
        private static final GameMng I = new GameMng();
    }

    public static GameMng getInstance() {
        return gameMng.I;
    }

    private long currentTime = System.currentTimeMillis();
    public int ground_y = 500;

    // 시간 제어 함수: 이 함수를 사용하면
    // 특정 시간 간격이 지났을 때만 어떠한 기능을 수행하게 할 수 있음
    // 매개변수: delay(기능을 실행할 시간 주기, 즉 딜레이. 단위: 초)
    public boolean timeCtrl(double delay) {
        long systemTime =  System.currentTimeMillis();
        if (currentTime + delay * 1000 <= systemTime) {
            currentTime = System.currentTimeMillis();
            return true;
        }
        else {
            return false;
        }
    }
}
