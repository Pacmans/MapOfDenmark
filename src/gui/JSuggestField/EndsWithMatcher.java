package gui.JSuggestField;

public class EndsWithMatcher implements SuggestMatcher {
	@Override
	public boolean matches(String dataWord, String searchWord) {
		return dataWord.endsWith(searchWord);
	}
}