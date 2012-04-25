package gui.JSuggestField;

public class StartsWithMatcher implements SuggestMatcher {
	@Override
	public boolean matches(String dataWord, String searchWord) {
		return dataWord.startsWith(searchWord);
	}
}