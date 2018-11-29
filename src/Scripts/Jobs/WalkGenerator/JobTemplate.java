package Scripts.Jobs.WalkGenerator;

import Scripts.Core.JobCore;
import Scripts.Tasks.Task;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name = "generate tile", description = "for testing purposes", properties = "author=triplejingle; topic=999; client=4;")

public class JobTemplate  extends PollingScript<ClientContext> implements KeyListener, MouseMotionListener, MouseListener{
    ArrayList<Tile> list = new ArrayList<>();
    public List<Task> taskList = new ArrayList();
    @Override
    public void poll() {
        execTasks();
    }

    public void execTasks(){
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task.activate()) {
                    task.execute();
                    break;
                }
            }

    }

    @Override
        public void start() {
            taskList.add(new TaskTemplate(ctx,list));
        }

    public void mouseClicked(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e) ) {
            printTiles();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    int index = 0;
    public void keyPressed(KeyEvent e) {
            if(e.getKeyChar()=='z'){
                System.out.println("deleting items");
                int listSize = list.size();
                for(int i = 0;i<listSize;i++){
                    list.remove(i);
                }
                if(index>0) {
                    index--;
                }
                System.out.println(index);
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void printTiles(){
        System.out.print("    Vertex g = createVertex(\"DraynorVillage"+index+"\");\n" +
                "Tile[] DraynorVillage = {");
            for(int i = 0;i<list.size();i++){
                if(i<(list.size())){
                    System.out.print("new Tile");
                }
                System.out.print(list.get(i));
                if(i<(list.size()-1)){
                    System.out.print(",");
                }
            }
        System.out.print("};");
            index++;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}




