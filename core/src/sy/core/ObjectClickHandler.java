package sy.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sy.input.prio.InputEvent;
import sy.input.prio.InputEventType;
import sy.input.prio.InputHandler;


//ToDo: needs a QuadTree here
public class ObjectClickHandler {
    private final int objectClickPriority = 50;
    private List<ObjectClickInformation> touchDownClickables = new ArrayList<>();
    private List<ObjectClickInformation> touchUpClickables = new ArrayList<>();
    private InputHandler inputHandler;
    private CameraData cameraData;
    private ObjectClickInformationComparator objectClickInformationComparator = new ObjectClickInformationComparator();

    public ObjectClickHandler(CameraData cameraData, InputHandler inputHandler) {
        this.cameraData = cameraData;
        this.inputHandler = inputHandler;
    }

    public void subscribeEvents() {
        this.inputHandler.addListener(InputEventType.TOUCH_DOWN, objectClickPriority,
                inputEvent -> invokeClicked(touchDownClickables, inputEvent));
        this.inputHandler.addListener(InputEventType.TOUCH_UP, objectClickPriority,
                inputEvent -> invokeClicked(touchUpClickables, inputEvent));
    }

    public void addTouchDownClickable(Clickable clickable, int priority, boolean isUiSpace) {
        touchDownClickables.add(new ObjectClickInformation(clickable, priority, isUiSpace));
        Collections.sort(touchDownClickables, objectClickInformationComparator);
    }

    public void addTouchUpClickable(Clickable clickable, int priority, boolean isUiSpace) {
        touchUpClickables.add(new ObjectClickInformation(clickable, priority, isUiSpace));
        Collections.sort(touchUpClickables, objectClickInformationComparator);
    }

    private void invokeClicked(List<ObjectClickInformation> objectClickInfos, InputEvent inputEvent) {
        int idx = 0;
        while(idx < objectClickInfos.size() && !inputEvent.isConsumed()) {
            ObjectClickInformation currInfo = objectClickInfos.get(idx);
            if(currInfo.contains(inputEvent.getX1(), inputEvent.getX2(), cameraData.getOrthographicCamera()))
                 currInfo.getClickable().onClicked(inputEvent);
            idx++;
        }
    }

    private static class ObjectClickInformationComparator implements Comparator<ObjectClickInformation> {
        @Override
        public int compare(ObjectClickInformation t1, ObjectClickInformation t2) {
            return Integer.compare(t1.getPriority(), t2.getPriority());
        }
    }
}
