package seedu.address.logic.commands.task;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.person.AddCommand;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Adds a Task to the address book.
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "addTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_PRIORITY + "PRIORITY "
            + PREFIX_CATEGORY + "CATEGORY "
            + PREFIX_DEADLINE + "DEADLINE"
            + PREFIX_PERSON + "PERSON "
            + PREFIX_STATUS + "STATUS...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Create Initial UI/UX Design"
            + PREFIX_DESCRIPTION + "Use FIGMA to create initial UI/UX Design"
            + PREFIX_PRIORITY + "Medium"
            + PREFIX_CATEGORY + "UI/UX"
            + PREFIX_DEADLINE + "2022-01-01"
            + PREFIX_PERSON + "Bob"
            + PREFIX_STATUS + "Not Done\n";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book";

    private final Task toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
