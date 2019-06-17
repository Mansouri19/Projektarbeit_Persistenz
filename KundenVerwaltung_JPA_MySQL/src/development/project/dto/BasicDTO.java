package development.project.dto;

public class BasicDTO extends Object {
	private static int id = 0;
	private int objID;
	private boolean touched;

	public BasicDTO() {
		this.objID = this.getNextDTO_ID();
	}

	private int getNextDTO_ID() {
		return ++id;
	}

	public int getID() {
		return this.objID;
	}

	public void markAsTouched(boolean touched) {
		this.touched = touched;
	}

	public boolean isTouched() {
		return this.touched;
	}
}
