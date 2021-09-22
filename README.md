## Test technique Rakuten

C'est un prototype de l’application Rakuten
Il contient deux écrans :
- le premier présente une barre de recherche et la liste des produits trouvés
- le second présente les détails d’un produit


En développant cette application, je me suis mis dans une situation de travail en supposant que les écrans sont déjà fournis.
L'application a été développée pour rassembler au maximum à l'application officielle.


Explication de mes choix:
- mise en place de `Clean Architecture` avec `MVVM`
- pour montrer mes connaissances en `RecyclerView`, j'ai utilisé `ListAdapter` et `DiffUtil.ItemCallback` pour la partie Products List pour une meilleure performance (comme il y aura beaucoup de produits) et d'autre part pour la partie Product Detail j'ai développé avec `RecyclerView.Adapter`
- pour montrer mes connaissances en `ViewModel` et `LiveData`, j'ai développé classes XXX et YYY différemment. Utilisation d'une classe `State` dans la partie Products List et des LiveDatas pour la partie Product Detail
- j'ai utilisé `Hilt` pour l'injection de dépendances, pour que l'application soit facilement testable (malheureusement je n'ai pas eu assez de temps pour faire les tests unitaires)