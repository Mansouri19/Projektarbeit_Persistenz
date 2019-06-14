package development.project.dto;

public class BasicDTO extends Object {
	private static int id = 0;
	private int objID;
	private boolean touched;

	/***********************************************************************************/
	public BasicDTO() {
		this.objID = this.getNextDTO_ID();
	}

	/***********************************************************************************/
	/**
	 * @return
	 */
	private int getNextDTO_ID() {
		return ++id;
	}

	/***********************************************************************************/
	/**
	 * @return
	 */
	public int getID() {
		return this.objID;
	}

	/***********************************************************************************/
	/**
	 * @param touched
	 */
	public void markAsTouched(boolean touched) {
		this.touched = touched;
	}

	/***********************************************************************************/
	/**
	 * @return
	 */
	public boolean isTouched() {
		return this.touched;
	}
}
