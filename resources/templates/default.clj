(let [config (config)
      blog-timespan (let [created (:date-created config)
                          now (+ (.getYear (java.util.Date.)) 1900)]
                      (if (= created now)
                        (str created)
                        (str created "-" now)))
      include-css (fn [link] [:link {:rel "stylesheet", :type "text/css",
                                    :href link}])]

  [:html {:xmlns "http://www.w3.org/1999/xhtml", :lang "en", :xml:lang "en"}

   [:head
    [:meta {:http-equiv "content-type", :content "text/html; charset=UTF-8"}]
    [:meta {:name "description", :content (:description metadata)}]
    [:meta {:name "keywords", :content (apply str (interpose "," (:tags metadata)))}]
    [:meta {:name "author", :content (:site-author config)}]
    [:link {:rel "icon", :href "/images/favicon.ico" :type "image/x-icon"}]
    [:link {:rel "shortcut icon",:href "/images/favicon.ico" :type "image/x-icon"}]
    (include-css "/default.css")
    (include-css "http://fonts.googleapis.com/css?family=Oswald")
    [:link {:rel "alternate", :type "application/rss+xml",
            :title (:site-title config), :href "/rss-feed"}]
    (if (= (:type metadata) :post)
      [:link {:rel "canonical" :href (str (:site-url config) (:url metadata))}])
    [:title (str (:title metadata) " - " (:site-title config))]

    [:script {:type "text/javascript"}
     "function lCSS(src){
   var stylesheet = document.createElement('link');
   stylesheet.href = src;
   stylesheet.rel = 'stylesheet';
   stylesheet.type = 'text/css';
   document.getElementsByTagName('head')[0].appendChild(stylesheet);}"]]
   [:body {:onload "lCSS('/tomorrow_night.css'); lCSS('/goodreads.css');"}
    [:div#masthead
     [:div#head
      [:div#top.clearfix
       [:div#blogname
        [:div#header.header.section
         [:div#Header1.widget.Header
          [:div#header-inner
           [:div.titlewrapper
            [:h1.title
             [:a {:href "/"} "Bytøpia"]]]
           [:div.descriptionwrapper
            [:p.description (:site-description config)]]]]]]

       [:div#contactlist
        [:a {:href "/rss-feed"} [:img {:src "/images/icons/rss.png"}]]
        [:a {:href "https://github.com/alexander-yakushev"} [:img {:src "/images/icons/github.png"}]]
        [:a {:href "mailto:alex@bytopia.org"} [:img {:src "/images/icons/email.png"}]]
        [:a {:href "https://twitter.com/unlog1c"} [:img {:src "/images/icons/twitter.png"}]]]]

      [:div#botmenu
       [:div#submenu.menu-primary-container
        [:div#nbttopmenu.nbttopmenu.section
         [:div#PageList8.widget.PageList
          [:div.widget-content
           (let [make-link (fn [link-name & {:keys [link title]
                                            :or {link (str "/" (.toLowerCase link-name) ".html")
                                                 title link-name}}]
                             [:li (if (and (= (:type metadata) :site)
                                           (= (:title metadata) title))
                                    {:class "current_page_item"} {})
                              [:a {:href link} link-name]])]
             [:ul#menu-primary.sfmenu
              (make-link "Home" :link "/")
              (make-link "About")
              (make-link "Projects")
              (make-link "Tags" :link "/tags/")
              (make-link "Archives")])
           [:div.clear]]]]]
       [:div#search
        [:form#searchform {:action "http://www.google.com/search", :method "get"}
         [:input {:type "hidden" :name "sitesearch", :value "www.bytopia.org"}]
         [:input {:id "s", :name "q",  :value "", :type "text"
                  :onblur "if(this.value==''){this.value='search site'};"
                  :onfocus "if(this.value=='search site'){this.value=''};"}]]]
       [:div.clear]]]]

    [:div#wrapper
     [:div#casing
      [:div#content
       [:div#mainblog.mainblog.section
        [:div#Blog1.widget.blog
         [:div.blog-posts.hfeed
          [:div.date-outer
           [:div.date-posts
            (if-not (= (:title metadata) "Home")
              [:div.post-outer
               [:div.post.hentry
                [:div.bposttitle
                 [:h2.post-title.entry-title (:title metadata)]
                 (when (= (:type metadata) :post)
                   [:div.entry-meta
                    [:span.entry-date (format-date (:date metadata) "dd MMMM YYYY")]
                    [:span.entry-tags "Tags: "
                     (->> (:tags metadata)
                          (map (fn [tag] [:a {:href (str "/tags/#" tag)} tag]))
                          (interpose " "))]
                    [:div.clear]])]

                [:div.entry
                 [:div.post-body.entry-summary
                  content
                  (when (= (:type metadata) :post)
                    [:div#comments
                     [:script {:data-isso "//bytopia.org/comments/"
                               :src "//bytopia.org/comments/js/embed.min.js"}]
                     [:section#isso-thread]])]]]]

              content)]]]]]]
      [:div#right
       [:div.sidebar
        [:div#sidebartop.sidebartop.section
         [:div.widget.HTML
          [:h2.title "Recent posts"]
          [:div.widget-content
           [:ul#recent-posts
            (for [f (take 10 (reverse (io/list-files :posts)))
                  :let [[metadata _] (io/read-doc f)]]
              [:li [:a {:href (post-url f)} (:title metadata)]])]]]
         [:div.widget.HTML
          [:h2.title "Tags"]
          [:div.widget-content
           [:ul#taglist {}
            (reduce (fn [div tag-kw]
                      (conj div [:a {:href (str "/tags/#" (name tag-kw))}
                                 (name tag-kw)] " "))
                    [:li#taglist]
                    (keys (tag-map)))
            ]]]
         (when (= (:title metadata) "Home")
           [:div.widget.HTML
            [:div#widgets
             [:div#goodreads
              [:script {:type "text/javascript", :charset "utf-8"
                        :src "https://www.goodreads.com/review/custom_widget/5694609.Recent%20books?cover_position=left&cover_size=small&num_books=5&order=d&shelf=read&show_author=1&show_cover=1&show_rating=0&show_review=0&show_tags=1&show_title=1&sort=date_read&widget_bg_color=FFFFFF&widget_bg_transparent=&widget_border_width=1&widget_id=13821383570&widget_text_color=000000&widget_title_size=medium&widget_width=medium"}]]
             [:div.clear]
             ]])
         ]]]
      [:div.clear]]]
    [:div#footer
     [:div.fcred "&copy; " blog-timespan " Alexander Yakushev"
      [:p.support "Powered by " [:a {:href "https://github.com/alexander-yakushev/discharge"} "Discharge"]
       " | Theme " [:a {:href "http://avenue-btemplates.blogspot.no/"} "Avenue"]
       " | Icons by " [:a {:href "https://dribbble.com/LeeGargano"} "LeeGargano"]]]
     [:div.clear]]

    "<!-- Piwik -->
<script type=\"text/javascript\">
  var _paq = _paq || [];
  _paq.push([\"trackPageView\"]);
  _paq.push([\"enableLinkTracking\"]);

  (function() {
    var u=\"//analytics.bytopia.org/\";
    _paq.push([\"setTrackerUrl\", u+\"jquery.min.php\"]);
    _paq.push([\"setSiteId\", 1]);
    var d=document, g=d.createElement(\"script\"), s=d.getElementsByTagName(\"script\")[0]; g.type=\"text/javascript\";
    g.defer=true; g.async=true; g.src=u+\"jquery.min.js\"; s.parentNode.insertBefore(g,s);
  })();
</script>
<!-- End Piwik Code -->"
    [:noscript [:p [:img {:src "//extras.bytopia.org/jquery.min.php?idsite=1",
                          :style "border:0", :alt ""}]]]]])
