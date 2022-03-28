package jslozano.recipe.converters;

import jslozano.recipe.commands.NotesCommand;
import jslozano.recipe.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if(source == null){
            return null;
        }
        final Notes notes = new Notes();
        notes.setId(notes.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}
