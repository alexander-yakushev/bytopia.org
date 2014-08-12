{:title "Tags"}

[:ul.posts
 (for [[tag files] (tag-map)]
   [:h4#tag tag
    [:ul (for [f files
               :let [url (post-url f)
                     title (:title (first (io/read-doc f)))]]
           [:li [:a {:href url} title]])]])]
