package tasklist;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import task.Task;
import task.stub.MarkedTaskStub;
import task.stub.TaskStub;
import task.stub.UnmarkedTaskStub;

class TaskListTest {

    @Test
    void addTask() {
        TaskList test = new TaskList();
        Task stub = new TaskStub();
        Task returnedStub = test.addTask(stub);
        assertAll(
                () -> assertSame(returnedStub, stub),
                () -> assertSame(stub, test.getItem(1))
        );
    }

    @Test
    void testToString_when_list_is_empty() {
        TaskList test = new TaskList();
        String expected = "\t " + "Here are the tasks in your list:" + "\n";
        assertEquals(expected, test.toString());
    }

    @Test
    void testToString_when_list_is_not_empty() {
        TaskList test = new TaskList();
        Task stub = new TaskStub();
        test.addTask(stub);

        String taskItemString = String.format("\t 1.%s\n", stub.toString());
        String expected = "\t " + "Here are the tasks in your list:" + "\n"
                + taskItemString;
        assertEquals(expected, test.toString());
    }

    @Test
    void markItem() {
        TaskList test = new TaskList();
        Task stub = new TaskStub();
        test.addTask(stub);
        Task markedStub = test.markItem(1);

        Task expectedStub = new MarkedTaskStub();
        expectedStub.setIsMarked(true);

        assertEquals(expectedStub, markedStub);
    }

    @Test
    void unmarkItem() {
        TaskList test = new TaskList();
        Task stub = new TaskStub();
        test.addTask(stub);
        test.markItem(1);
        Task actualStub = test.unmarkItem(1);
        Task expectedStub = new UnmarkedTaskStub();
        assertEquals(actualStub, expectedStub);
    }

    @Test
    void deleteItem_giving_empty_list() {
        TaskList test = new TaskList();
        Task stub = new TaskStub();
        test.addTask(stub);
        test.deleteItem(1);

        String expected = "\t " + "Here are the tasks in your list:" + "\n";
        assertEquals(expected, test.toString());
    }

    @Test
    void getTaskCount_when_list_is_empty() {
        TaskList test = new TaskList();
        assertEquals(test.getTaskCount(), 0);
    }

    @Test
    void getTaskCount_when_list_has_one_task() {
        TaskList test = new TaskList();
        Task stub = new TaskStub();
        test.addTask(stub);
        assertEquals(test.getTaskCount(), 1);
    }
}