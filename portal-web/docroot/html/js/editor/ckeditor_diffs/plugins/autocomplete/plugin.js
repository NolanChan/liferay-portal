;(function() {
	CKEDITOR.plugins.add(
		'autocomplete',
		{
			init: function(editor) {
				var instance = this;

				AUI().use(
					'liferay-autocomplete-input',
					function(A) {
						var path = instance.path;

						var dependencies = [
							CKEDITOR.getUrl(path + 'autocomplete.js')
						];

						CKEDITOR.scriptLoader.load(
							dependencies,
							function() {
								new Liferay.AutoCompleteCKEditor(
									A.merge(
										editor.config.autocomplete,
										{
											editor: editor
										}
									)
								).render();
							}
						);
					}
				);
			}
		}
	);
})();