# BootstrapModernLook

This framework is designed to offer bootstrap components and tagging for very large-scale industrial database applications of very large size, such as iTunes Store and App Store or other high-availability enterprise level systems.

It is made to support modern Bootstrap (v.4.1) and specifically Shards style UI using ERDModernLook. This framework provides the D2W Custom Rules and Components to override where needed, and to provide completely modern UI for forms, tables navigation bars, carousels and cards —all available via D2W.

https://designrevision.com/downloads/shards/

https://getbootstrap.com/docs/4.1/getting-started/introduction/

Components accept rule based css tagging for you to adjust the styling of any D2W List Page or input form etc., you might adjust a rule like this to control the cancel button look according to shards button look

https://designrevision.com/docs/shards/buttons.html

<code>
     
      *true* => baseClassForObjectTable = "table table-borderless table-md"

</code>


There are also new components for UI elements such as Datepickers, Cards, Buttons, Carousels, Progress Bars and FontAwesome glyphs inside of Input fields. D2W Rules control button styles, or provide appending glyphs for forms.

https://designrevision.com/docs/shards/cards.html

https://designrevision.com/docs/shards/using-icons.html

<code>
     
      *true* => baseClassForCancelButtonSpan = "fa fa-sign-in mr-2"

</code>


Rules control complex multi-part components, such as Cards or Carousels which have optional titles, text areas, footers, images in any compbination. By providing titles or keypath selector strings in the form of a dictionary, where keys are named after their css controll counterparts found in Bootsrap and Shards.

https://designrevision.com/docs/shards/cards.html

<code>
     
      entity.name = 'Post' => cardSectionsContents = {
            "card-img-top" = "object.imageURL"; 
            "card-link" = "object.publicURLString"; 
            "card-text" = "object.title"; 
            "card-title" = "object.venue.title"; 
            "cardLInkTitle" = "Tell me more &rarr;"; 
      }
     
</code>

      
Keypath selector keys controlling Bootsrap elements return values based on the current object and context. Other Keypath selector keys return normal text/html string details, such as cardLInkTitle

<code>
     
      "cardLInkTitle" = "Tell me more &rarr;"; 

</code>


Each component has extends this metaphore so a single rule can control an arbitrarilty complex UI copmonent.

A simple D2W Rule to set "repetitionComonentName" to either "BMLCardRepetition" or "BMLCarouselRepetition" and turn any list into a complex card or carousel. Empty list messages and batching controls are maintained and styled.

<code>
     
      60 : (pageConfiguration = 'ListPost-Author' or pageConfiguration = 'ListBookmark-Author') => repetitionComponentName = BMLCardRepetition

</code>


For your page wrapper you will need to load several js and css files in your header:

<code>
     
      <!-- Modern Look Overrides for Shards and Bootstrap -->
      <wo:ERXStyleSheet framework = "BootstrapModernLook" filename = "ermod-overrides.css" />
      
      <!-- App Level css rules and overrides -->
      <wo:ERXStyleSheet framework = "app" filename = "bootstrap-overrides-10.css" />
      
      <!-- Google Maps. Share it and JQuery - Load in the header -->
      <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
      <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/social-share-kit/1.0.15/js/social-share-kit.min.js"></script>
      <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdHbqIWdKO_kJg3hnYbldZ3qaUotXBzA4&libraries=places"></script>

      <!-- jQuery UI library with the following - Load in the header   -->
      <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
      <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/shards-ui@2.0.3/dist/js/shards.min.js"></script>

</code>

Popper and Bootstrap must load inside the body area of your page:

<code>
     
      <!-- These JS files must load in the body  -->
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
      <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</code>


![Screenshot](screenshot2.png)

