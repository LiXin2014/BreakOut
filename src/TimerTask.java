
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.Timer;

public class TimerTask implements Observable {
	private Timer t;
	private Board board;
	private Invoker invoker;
	private CommandManager cmdManager;
	private CopyOnWriteArrayList<ComponentObserver> observerList;
	private CopyOnWriteArrayList<Observer> gameObserverList;

	public TimerTask(Board b) {
		board = b;
		t = new Timer(10, new TimerTaskListener());
		observerList = new CopyOnWriteArrayList<ComponentObserver>();
		gameObserverList = new CopyOnWriteArrayList<Observer>();
		invoker = new Invoker();
		cmdManager = board.getCmdManager();
	}

	public void run() {
		t.start();
	}

	class TimerTaskListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			notifyObservers();
		}
	}

	@Override
	public void register(Observer observer) {
		if(observer instanceof Replay)
		{
			gameObserverList.add(observer);
		}
		else
		{
			observerList.add((ComponentObserver)observer);
		}
	}

	@Override
	public void unRegister(Observer observer) {
		if(observer instanceof Replay)
		{
			gameObserverList.remove(observer);
		}
		else
		{
			observerList.remove((ComponentObserver)observer);
		}
	}

	@Override
	public void notifyObservers() {
		if(observerList.size() != 0)
		{
			ArrayList<Command> cmdList =  null;
			ComponentObserver observer;
			cmdList = new ArrayList<Command>();
			Iterator<ComponentObserver> Obs = observerList.iterator();
			while (Obs.hasNext()) {
				observer = Obs.next();
				Command cmd = observer.getCommand();
				if(cmd != null)
				{
					invoker.setCommand(cmd);
					cmdList.add(cmd);
					invoker.invoke();
				}
			}
			cmdManager.addCommand(cmdList);			
		}
		else if(gameObserverList.size() != 0)
		{
			Observer gameObserver;
			Iterator<Observer> Obs = gameObserverList.iterator();
			while (Obs.hasNext()) {
				gameObserver = Obs.next();
				gameObserver.update();
			}
		}
	}

	public void undoOperation(ArrayList<Command> undoList) {
		for (Command cmd : undoList) {
			invoker.setCommand(cmd);
			invoker.revoke();
		}
	}
}
