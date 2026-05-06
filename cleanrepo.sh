#!/bin/bash

echo "Git-aware cleanup starting"

# Remove tracked junk files from Git index only
FILES=$(git ls-files | grep -E "\.DS_Store|\.class|\.iml|\.log")

if [ -n "$FILES" ]; then
  echo "$FILES" | while read -r file; do
    echo "Removing tracked: $file"
    git rm --cached "$file" 2>/dev/null
  done
else
  echo "No tracked junk found."
fi

echo "Git cleanup complete"
