{:title "Archives"}

[:ul.posts
 (for [[date posts] (posts-by-month)]
   [:h4 (format-date date "yyyy-MM" "MMMM yyyy")
    [:ul (for [f posts]
           [:li [:a {:href (post-url f)}
                 (:title (first (io/read-doc f)))]])]])]
